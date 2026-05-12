import java.util.ArrayList;
import java.util.List;

public class AttendanceTracker {
    private List<Doctor> doctors;
    private List<AttendanceRecord> records;

    public AttendanceTracker() {
        this.doctors = new ArrayList<>();
        this.records = new ArrayList<>();
    }

    public void addDoctor(Doctor d) {
        doctors.add(d);
    }

    public void markAttendance(AttendanceRecord r) {
        records.add(r);
    }

    public int getTotalDays(int doctorId) {
        int count = 0;
        for (AttendanceRecord record : records) {
            if (record.getDoctorId() == doctorId) {
                count++;
            }
        }
        return count;
    }

    public int getPresentCount(int doctorId) {
        int count = 0;
        for (AttendanceRecord record : records) {
            if (record.getDoctorId() == doctorId && "PRESENT".equalsIgnoreCase(record.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public int getAbsentCount(int doctorId) {
        int count = 0;
        for (AttendanceRecord record : records) {
            if (record.getDoctorId() == doctorId && "ABSENT".equalsIgnoreCase(record.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public int getLeaveCount(int doctorId) {
        int count = 0;
        for (AttendanceRecord record : records) {
            if (record.getDoctorId() == doctorId && "LEAVE".equalsIgnoreCase(record.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public double calculateAttendancePercentage(int doctorId) {
        int totalDays = getTotalDays(doctorId);
        if (totalDays == 0) {
            return 0.0;
        }
        int presentCount = getPresentCount(doctorId);
        return ((double) presentCount / totalDays) * 100;
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    public Doctor findDoctorById(int doctorId) {
        for (Doctor d : doctors) {
            if (d.getDoctorId() == doctorId) {
                return d;
            }
        }
        return null;
    }

    public List<AttendanceRecord> getAttendanceByMonth(int doctorId, String month) {
        List<AttendanceRecord> monthlyRecords = new ArrayList<>();
        for (AttendanceRecord record : records) {
            if (record.getDoctorId() == doctorId && record.getDate().contains(month)) {
                monthlyRecords.add(record);
            }
        }
        return monthlyRecords;
    }
}
