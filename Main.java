import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create AttendanceTracker instance
        AttendanceTracker tracker = new AttendanceTracker();

        // 2. Create 3 sample Doctor objects
        Doctor d1 = new Doctor(101, "Dr. Alice Smith", "Cardiology", "Surgeon");
        Doctor d2 = new Doctor(102, "Dr. Bob Johnson", "Neurology", "Specialist");
        Doctor d3 = new Doctor(103, "Dr. Charlie Brown", "Pediatrics", "Consultant");

        // 3. Add all doctors to the tracker
        tracker.addDoctor(d1);
        tracker.addDoctor(d2);
        tracker.addDoctor(d3);

        // 4. Create 10 to 12 AttendanceRecord objects spread across all 3 doctors
        // Dr. Alice Smith (101) - 4 records
        tracker.markAttendance(new AttendanceRecord(1, 101, "2026-05-01", "PRESENT"));
        tracker.markAttendance(new AttendanceRecord(2, 101, "2026-05-02", "PRESENT"));
        tracker.markAttendance(new AttendanceRecord(3, 101, "2026-05-03", "PRESENT"));
        tracker.markAttendance(new AttendanceRecord(4, 101, "2026-05-04", "PRESENT")); // 100%

        // Dr. Bob Johnson (102) - 4 records
        tracker.markAttendance(new AttendanceRecord(5, 102, "2026-05-01", "PRESENT"));
        tracker.markAttendance(new AttendanceRecord(6, 102, "2026-05-02", "ABSENT"));
        tracker.markAttendance(new AttendanceRecord(7, 102, "2026-05-03", "PRESENT"));
        tracker.markAttendance(new AttendanceRecord(8, 102, "2026-05-04", "LEAVE")); // 50%

        // Dr. Charlie Brown (103) - 4 records
        tracker.markAttendance(new AttendanceRecord(9, 103, "2026-05-01", "PRESENT"));
        tracker.markAttendance(new AttendanceRecord(10, 103, "2026-05-02", "PRESENT"));
        tracker.markAttendance(new AttendanceRecord(11, 103, "2026-05-03", "PRESENT"));
        tracker.markAttendance(new AttendanceRecord(12, 103, "2026-05-04", "ABSENT")); // 75%

        // 5. Create a ReportGenerator with the tracker
        ReportGenerator reportGenerator = new ReportGenerator(tracker);

        // 6. Call displayAllReports()
        System.out.println("=== GENERATING ALL ATTENDANCE REPORTS ===\n");
        reportGenerator.displayAllReports();

        // 7. Print the doctor with highest attendance
        Doctor highest = reportGenerator.getHighestAttendanceDoctor();
        if (highest != null) {
            System.out.println("=== DOCTOR WITH HIGHEST ATTENDANCE ===");
            System.out.println("Name: " + highest.getName());
            System.out.printf("Percentage: %.2f%%\n", tracker.calculateAttendancePercentage(highest.getDoctorId()));
            System.out.println();
        }

        // 8. Print doctors below 75 percent attendance threshold
        double threshold = 75.0;
        System.out.println("=== DOCTORS BELOW " + threshold + "% ATTENDANCE ===");
        List<Doctor> lowAttendanceDoctors = reportGenerator.getLowAttendanceDoctors(threshold);
        if (lowAttendanceDoctors.isEmpty()) {
            System.out.println("None found.");
        } else {
            for (Doctor d : lowAttendanceDoctors) {
                System.out.printf("- %s (%s): %.2f%%\n", 
                    d.getName(), 
                    d.getDepartment(), 
                    tracker.calculateAttendancePercentage(d.getDoctorId()));
            }
        }

        // 9. Demo Monthly Report
        System.out.println("\n=== MONTHLY REPORT DEMO (MAY 2026) ===");
        // Adding one record for June to test filtering
        tracker.markAttendance(new AttendanceRecord(13, 101, "2026-06-01", "PRESENT"));
        
        reportGenerator.displayMonthlyReport(101, "2026-05");
    }
}
