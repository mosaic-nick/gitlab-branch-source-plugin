package io.jenkins.plugins.gitlabbranchsource;

import hudson.Extension;
import java.io.IOException;
import jenkins.branch.MultiBranchProjectFactory;
import jenkins.branch.MultiBranchProjectFactoryDescriptor;
import jenkins.scm.api.SCMSource;
import jenkins.scm.api.SCMSourceCriteria;
import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.workflow.multibranch.AbstractWorkflowMultiBranchProjectFactory;
import org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

public class CustomWorkflowMultiBranchProjectFactory extends AbstractWorkflowMultiBranchProjectFactory {
    private String scriptPath = CustomWorkflowBranchProjectFactory.SCRIPT;

    public Object readResolve() {
        if (this.scriptPath == null) {
            this.scriptPath = CustomWorkflowBranchProjectFactory.SCRIPT;
        }
        return this;
    }

    @DataBoundSetter
    public void setScriptPath(String scriptPath) {
        if (StringUtils.isEmpty(scriptPath)) {
            this.scriptPath = CustomWorkflowBranchProjectFactory.SCRIPT;
        } else {
            this.scriptPath = scriptPath;
        }
    }

    public String getScriptPath() { return scriptPath; }

    @DataBoundConstructor
    public CustomWorkflowMultiBranchProjectFactory() { }

    @Override protected SCMSourceCriteria getSCMSourceCriteria(SCMSource source) {
        return newProjectFactory().getSCMSourceCriteria(source);
    }

    private CustomWorkflowBranchProjectFactory newProjectFactory() {
        CustomWorkflowBranchProjectFactory workflowBranchProjectFactory = new CustomWorkflowBranchProjectFactory();
        workflowBranchProjectFactory.setScriptPath(scriptPath);
        return workflowBranchProjectFactory;
    }

    @Extension public static class DescriptorImpl extends MultiBranchProjectFactoryDescriptor {

        @Override public MultiBranchProjectFactory newInstance() {
            return new CustomWorkflowMultiBranchProjectFactory();
        }

        @Override public String getDisplayName() {
            return "Pipeline " + CustomWorkflowBranchProjectFactory.SCRIPT + " (MSD)";
        }

    }

    @Override
    protected void customize(WorkflowMultiBranchProject project) throws IOException, InterruptedException {
        project.setProjectFactory(newProjectFactory());
    }
}
