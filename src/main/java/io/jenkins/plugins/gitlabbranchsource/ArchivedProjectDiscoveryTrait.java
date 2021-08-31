package io.jenkins.plugins.gitlabbranchsource;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import jenkins.scm.api.SCMNavigator;
import jenkins.scm.api.trait.SCMNavigatorContext;
import jenkins.scm.api.trait.SCMNavigatorTrait;
import jenkins.scm.api.trait.SCMNavigatorTraitDescriptor;
import jenkins.scm.impl.trait.Discovery;
import org.kohsuke.stapler.DataBoundConstructor;

public class ArchivedProjectDiscoveryTrait extends SCMNavigatorTrait {

    @DataBoundConstructor
    public ArchivedProjectDiscoveryTrait() {
        // empty
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void decorateContext(SCMNavigatorContext<?, ?> context) {
        if (context instanceof GitLabSCMNavigatorContext) {
            GitLabSCMNavigatorContext ctx = (GitLabSCMNavigatorContext) context;
            ctx.wantArchivedProjects(true);
        }
    }

    /**
     * Our descriptor.
     */
    @Extension
    @Discovery
    public static class DescriptorImpl extends SCMNavigatorTraitDescriptor {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public String getDisplayName() {
            return Messages.ArchivedProjectDiscoveryTrait_displayName();
        }

        @Override
        public Class<? extends SCMNavigator> getNavigatorClass() {
            return GitLabSCMNavigator.class;
        }
    }
}
