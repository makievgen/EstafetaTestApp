
package ram1991.estafetatestapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Task implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("PlannedStartDate")
    @Expose
    private String plannedStartDate;
    @SerializedName("PlannedEndDate")
    @Expose
    private String plannedEndDate;
    @SerializedName("ActualStartDate")
    @Expose
    private String actualStartDate;
    @SerializedName("ActualEndDate")
    @Expose
    private String actualEndDate;
    @SerializedName("Vin")
    @Expose
    private String vin;
    @SerializedName("Model")
    @Expose
    private String model;
    @SerializedName("ModelCode")
    @Expose
    private String modelCode;
    @SerializedName("Brand")
    @Expose
    private String brand;
    @SerializedName("SurveyPoint")
    @Expose
    private String surveyPoint;
    @SerializedName("Carrier")
    @Expose
    private String carrier;
    @SerializedName("Driver")
    @Expose
    private String driver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(String plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public String getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(String plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public String getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public String getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(String actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSurveyPoint() {
        return surveyPoint;
    }

    public void setSurveyPoint(String surveyPoint) {
        this.surveyPoint = surveyPoint;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

}
