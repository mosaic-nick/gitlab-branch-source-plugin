package io.jenkins.plugins.gitlabbranchsource;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import jenkins.scm.api.SCMSource;
import jenkins.scm.api.trait.SCMSourceContext;
import jenkins.scm.api.trait.SCMSourceTrait;
import jenkins.scm.api.trait.SCMSourceTraitDescriptor;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

public class GitLabSkipNotificationsOnBranchesTrait extends SCMSourceTrait {

    private final String branchPattern;

    /**
     * Constructor for stapler.
     */
    @DataBoundConstructor
    public GitLabSkipNotificationsOnBranchesTrait(String branchPattern) {
        this.branchPattern = branchPattern;
    }

    public String getBranchPattern() {
        return branchPattern;
    }

    @Override
    protected void decorateContext(SCMSourceContext<?, ?> context) {
        if (context instanceof GitLabSCMSourceContext) {
            GitLabSCMSourceContext ctx = (GitLabSCMSourceContext) context;

            ctx.withNotificationsDisabledOnBranches(branchPattern);
        }
    }

    /**
     * Our descriptor.
     */
    @Extension
    @Symbol("gitlabSkipNotificationsOnBranches")
    public static class DescriptorImpl extends SCMSourceTraitDescriptor {

        @NonNull
        @Override
        public String getDisplayName() {
            return Messages.GitLabSkipNotificationsOnBranchesTrait_displayName();
        }

        @Override
        public Class<? extends SCMSourceContext> getContextClass() {
            return GitLabSCMSourceContext.class;
        }

        @Override
        public Class<? extends SCMSource> getSourceClass() {
            return GitLabSCMSource.class;
        }

    }

}
