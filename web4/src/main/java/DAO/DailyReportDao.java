package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public DailyReport getLastReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = (List<DailyReport>) session.createQuery("FROM DailyReport").list();
        DailyReport dailyReport = dailyReports.get(dailyReports.size() - 1);
        transaction.commit();
        session.close();
        return dailyReport;
    }

    public void dailyReportAdd(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();

    }

    public void dailyReportDelete() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();
        transaction.commit();
        session.close();

    }

}
