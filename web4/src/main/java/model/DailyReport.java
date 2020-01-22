package model;

import javax.persistence.*;

@Entity
@Table(name = "daily_reports")
public class DailyReport {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "earnings")
    private Long earnings;

    @Column(name = "soldCars")
    private Long soldCars;

    private DailyReport() {

    }

    private static DailyReport dailyReport;

    public static DailyReport instance() {
        if (dailyReport == null)
            dailyReport = new DailyReport();
        return dailyReport;
    }

    private DailyReport(Long earnings, Long soldCars) {
        this.earnings = earnings;
        this.soldCars = soldCars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings += earnings;
    }

    public Long getSoldCars() {
        return soldCars;
    }

    public void setSoldCars(Long soldCars) {
        this.soldCars += soldCars;
    }

    public void delDailyReports() {
        this.earnings = 0L;
        this.soldCars = 0L;
    }
}
