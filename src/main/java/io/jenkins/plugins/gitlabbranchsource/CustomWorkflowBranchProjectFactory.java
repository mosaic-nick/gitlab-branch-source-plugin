package io.jenkins.plugins.gitlabbranchsource;

import hudson.Extension;
import java.io.IOException;
import jenkins.branch.Branch;
import jenkins.scm.api.SCMHead;
import jenkins.scm.api.SCMSource;
import jenkins.scm.api.SCMSourceCriteria;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.multibranch.WorkflowBranchProjectFactory;
import org.kohsuke.stapler.DataBoundConstructor;

public class CustomWorkflowBranchProjectFactory extends WorkflowBranchProjectFactory {
    static final String SCRIPT = "Jenkinsfile";

    @DataBoundConstructor
    public CustomWorkflowBranchProjectFactory() {
    }

    @Override
    public WorkflowJob newInstance(Branch branch) {
        WorkflowJob job = super.newInstance(branch);
        SCMHead head = branch.getHead();
        if (head instanceof MergeRequestSCMHead) {
            MergeRequestSCMHead mergeRequestSCMHead = (MergeRequestSCMHead) head;
            try {
                job.setDisplayName(branch.getName() + " (" + mergeRequestSCMHead.getOriginName() + " → " + mergeRequestSCMHead.getTarget().getName() + ")");
            } catch (IOException e) {
                // do something
            }
        }
        return job;
    }

    @Override
    protected SCMSourceCriteria getSCMSourceCriteria(SCMSource source) {
        return super.getSCMSourceCriteria(source);
    }

    @Extension
    public static class DescriptorImpl extends AbstractWorkflowBranchProjectFactoryDescriptor {

        @Override public String getDisplayName() {
            return "by Jenkinsfile (MSD)";
        }

    }
}
