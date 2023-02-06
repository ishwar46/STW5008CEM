import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PipelineJob {

    private final JFrame frame = new JFrame();
    private final JButton startButton = new JButton("Start");
    private final List<Job> jobs = new ArrayList<>();

    public PipelineJob() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.add(startButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        executeJobs();
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                startButton.setEnabled(true);
                            }
                        });
                    }
                }).start();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    public void executeJobs() {
        for (Job job : jobs) {
            job.run();
        }
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public static class Job {
        private final int id;

        public Job(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println("Job " + id + " is running");
            // perform some task here
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PipelineJob pipeline = new PipelineJob();
                pipeline.addJob(new Job(1));
                pipeline.addJob(new Job(2));
                pipeline.addJob(new Job(3));
            }
        });
    }
}
