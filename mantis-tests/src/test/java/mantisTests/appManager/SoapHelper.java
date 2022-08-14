package mantisTests.appManager;

import biz.futureware.mantis.rpc.soap.client.*;
import mantisTests.model.Issue;
import mantisTests.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects =
                mc.mc_projects_get_user_accessible(app.getProperty("admin.login"), app.getProperty("admin.password"));
        return Arrays.asList(projects).stream().map((p) ->
                new Project()
                        .withId(p.getId().intValue())
                        .withName(p.getName()))
                .collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("soap.connectUrl")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories =
                mc.mc_project_get_categories(app.getProperty("admin.login"), app.getProperty("admin.password"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(app.getProperty("admin.login"), app.getProperty("admin.password"), issueData);
        return getIssue(issueId.intValue());
    }

    public Issue getIssue(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData createdIssueData =
                mc.mc_issue_get(app.getProperty("admin.login"), app.getProperty("admin.password"), BigInteger.valueOf(id));
        return new Issue()
                .withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription())
                .withProject(new Project()
                        .withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));
    }

    public boolean checkIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String status =
                mc.mc_issue_get(app.getProperty("admin.login"), app.getProperty("admin.password"), BigInteger.valueOf(issueId)).getStatus().getName();
        return !"resolved".equals(status);
    }
}
