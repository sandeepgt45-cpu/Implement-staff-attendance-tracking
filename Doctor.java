public class Doctor {
    private int doctorId;
    private String name;
    private String department;
    private String specialization;

    public Doctor(int doctorId, String name, String department, String specialization) {
        this.doctorId = doctorId;
        this.name = name;
        this.department = department;
        this.specialization = specialization;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor Details:\n" +
               "ID: " + doctorId + "\n" +
               "Name: " + name + "\n" +
               "Department: " + department + "\n" +
               "Specialization: " + specialization;
    }
}
