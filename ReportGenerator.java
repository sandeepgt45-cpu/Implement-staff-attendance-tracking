import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
    private AttendanceTracker tracker;

    public ReportGenerator(AttendanceTracker tracker) {
        this.tracker = tracker;
    }

    public void displayDoctorReport(int doctorId) {
        Doctor doctor = tracker.findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor with ID " + doctorId + " not found.");
            return;
        }

        System.out.println("----- Doctor Attendance Report -----");
        System.out.println("Name: " + doctor.getName());
        System.out.println("Department: " + doctor.getDepartment());
        System.out.println("Total Days Tracked: " + tracker.getTotalDays(doctorId));
        System.out.println("Present Count: " + tracker.getPresentCount(doctorId));
        System.out.println("Absent Count: " + tracker.getAbsentCount(doctorId));
        System.out.println("Leave Count: " + tracker.getLeaveCount(doctorId));
        System.out.printf("Attendance Percentage: %.2f%%\n", tracker.calculateAttendancePercentage(doctorId));
        System.out.println("------------------------------------");
    }

    public void displayAllReports() {
        for (Doctor d : tracker.getDoctors()) {
            displayDoctorReport(d.getDoctorId());
            System.out.println();
        }
    }

    public Doctor getHighestAttendanceDoctor() {
        Doctor highest = null;
        double maxPercent = -1.0;

        for (Doctor d : tracker.getDoctors()) {
            double percent = tracker.calculateAttendancePercentage(d.getDoctorId());
            if (percent > maxPercent) {
                maxPercent = percent;
                highest = d;
            }
        }
        return highest;
    }

    public List<Doctor> getLowAttendanceDoctors(double minPercent) {
        List<Doctor> lowAttendance = new ArrayList<>();
        for (Doctor d : tracker.getDoctors()) {
            double percent = tracker.calculateAttendancePercentage(d.getDoctorId());
            if (percent < minPercent) {
                lowAttendance.add(d);
            }
        }
        return lowAttendance;
    }

    public void displayMonthlyReport(int doctorId, String month) {
        Doctor doctor = tracker.findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor with ID " + doctorId + " not found.");
            return;
        }

        List<AttendanceRecord> monthlyRecords = tracker.getAttendanceByMonth(doctorId, month);
        int total = monthlyRecords.size();
        int present = 0;
        int absent = 0;
        int leave = 0;

        for (AttendanceRecord r : monthlyRecords) {
            if ("PRESENT".equalsIgnoreCase(r.getStatus())) present++;
            else if ("ABSENT".equalsIgnoreCase(r.getStatus())) absent++;
            else if ("LEAVE".equalsIgnoreCase(r.getStatus())) leave++;
        }

        double percentage = (total == 0) ? 0.0 : ((double) present / total) * 100;

        System.out.println("----- Monthly Attendance Report (" + month + ") -----");
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Total Days: " + total);
        System.out.println("Present: " + present);
        System.out.println("Absent: " + absent);
        System.out.println("Leave: " + leave);
        System.out.printf("Monthly Percentage: %.2f%%\n", percentage);
        System.out.println("----------------------------------------------");
    }
}
