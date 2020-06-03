package io.jenkins.plugins.gitlabserverconfig.credentials;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.Run;
import hudson.model.TaskListener;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.credentialsbinding.Binding;
import org.jenkinsci.plugins.credentialsbinding.BindingDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

public class GitLabPersonalAccessTokenBinding extends Binding<PersonalAccessToken> {

    @DataBoundConstructor
    public GitLabPersonalAccessTokenBinding(String variable, String credentialsId) {
        super(variable, credentialsId);
    }

    @Override
    protected Class<PersonalAccessToken> type() {
        return PersonalAccessToken.class;
    }

    @Override
    public SingleEnvironment bindSingle(@Nonnull Run<?, ?> build,
        @Nullable FilePath workspace,
        @Nullable Launcher launcher,
        @Nonnull TaskListener listener) throws IOException, InterruptedException {
        PersonalAccessToken credentials = getCredentials(build);
        return new SingleEnvironment(credentials.getToken().getPlainText());
    }

    @Extension
    @Symbol("gitlabPersonalAccessToken")
    public static class DescriptorImpl extends BindingDescriptor<PersonalAccessToken> {

        @Override
        protected Class<PersonalAccessToken> type() {
            return PersonalAccessToken.class;
        }

        @Override
        public boolean requiresWorkspace() {
            return false;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return Messages.PersonalAccessTokenImpl_displayName();
        }
    }
}
