package io.jenkins.plugins.gitlabbranchsource;

import edu.umd.cs.findbugs.annotations.NonNull;
import jenkins.scm.api.SCMNavigator;
import jenkins.scm.api.SCMSourceObserver;
import jenkins.scm.api.trait.SCMNavigatorRequest;

public class GitLabSCMNavigatorRequest extends SCMNavigatorRequest {

    private boolean wantSubgroupProjects;

    private int projectNamingStrategy;

    private final boolean wantArchivedProjects;

    protected GitLabSCMNavigatorRequest(@NonNull SCMNavigator source,
        @NonNull GitLabSCMNavigatorContext context,
        @NonNull SCMSourceObserver observer) {
        super(source, context, observer);
        wantSubgroupProjects = context.wantSubgroupProjects();
        projectNamingStrategy = context.withProjectNamingStrategy();
        wantArchivedProjects = context.wantArchivedProjects();
    }

    /**
     * @return whether to include subgroup projects
     */
    public boolean wantSubgroupProjects() {
        return wantSubgroupProjects;
    }

    /**
     * Returns the project naming strategy id.
     *
     * @return the project naming strategy id.
     */
    public int withProjectNamingStrategy() {
        return projectNamingStrategy;
    }

    /**
     * @return whether to include archived projects
     */
    public boolean wantArchivedProjects() {
        return wantArchivedProjects;
    }
}
