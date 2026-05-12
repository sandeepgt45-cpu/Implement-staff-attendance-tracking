public class AttendanceRecord {
    private int recordId;
    private int doctorId;
    private String date;
    private String status; // Values: PRESENT, ABSENT, LEAVE

    public AttendanceRecord(int recordId, int doctorId, String date, String status) {
        this.recordId = recordId;
        this.doctorId = doctorId;
        this.date = date;
        this.status = status;
    }

    public int getRecordId() {
        return recordId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Attendance Record:\n" +
               "Record ID: " + recordId + "\n" +
               "Doctor ID: " + doctorId + "\n" +
               "Date: " + date + "\n" +
               "Status: " + status;
    }
}
