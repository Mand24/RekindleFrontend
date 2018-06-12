package com.example.usuario.rekindlefrontend.data.entity.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Job extends Service {
    @SerializedName("charge")
    @Expose
    public String charge;
    @SerializedName("requirements")
    @Expose
    public String requirements;
    @SerializedName("hoursDay")
    @Expose
    public String hoursDay;
    @SerializedName("hoursWeek")
    @Expose
    public String hoursWeek;
    @SerializedName("contractDuration")
    @Expose
    public String contractDuration;
    @SerializedName("places")
    @Expose
    public String placesLimit;
    @SerializedName("salary")
    @Expose
    public String salary;

    public Job(int id, String email, String name, String description, String adress, String charge,
            String requirements, String hoursDay, String hoursWeek, String contractDuration, String
            placesLimit, String salary, String phoneNumber, Boolean
            ended) {
        super(id, "Job", email, name, description, adress, phoneNumber, ended);
        this.charge = charge;
        this.requirements = requirements;
        this.hoursDay = hoursDay;
        this.hoursWeek = hoursWeek;
        this.contractDuration = contractDuration;
        this.placesLimit = placesLimit;
        this.salary = salary;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getHoursDay() {
        return hoursDay;
    }

    public void setHoursDay(String hoursDay) {
        this.hoursDay = hoursDay;
    }

    public String getHoursWeek() {
        return hoursWeek;
    }

    public void setHoursWeek(String hoursWeek) {
        this.hoursWeek = hoursWeek;
    }

    public String getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(String contractDuration) {
        this.contractDuration = contractDuration;
    }

    public String getPlacesLimit() {
        return placesLimit;
    }

    public void setPlacesLimit(String placesLimit) {
        this.placesLimit = placesLimit;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + getId() + '\'' +
                ", serviceType=" + getServiceType() + '\'' +
                ", volunteer='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", adress='" + getAdress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", charge='" + charge + '\'' +
                ", requirements='" + requirements + '\'' +
                ", hoursDay='" + hoursDay + '\'' +
                ", hoursWeek='" + hoursWeek + '\'' +
                ", contractDuration='" + contractDuration + '\'' +
                ", placesLimit='" + placesLimit + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
