package io.jenkins.plugins.gitlabserverconfig.credentials;

import com.cloudbees.plugins.credentials.CredentialsDescriptor;
import com.cloudbees.plugins.credentials.CredentialsScope;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.util.Secret;
import org.jenkinsci.plugins.plaincredentials.StringCredentials;

public class StringPersonalAccessTokenWrapper implements PersonalAccessToken {
    private final StringCredentials stringCredentials;

    public StringPersonalAccessTokenWrapper(
        StringCredentials stringCredentials) {
        this.stringCredentials = stringCredentials;
    }

    @NonNull
    @Override
    public Secret getToken() {
        return stringCredentials.getSecret();
    }

    @NonNull
    @Override
    public String getDescription() {
        return stringCredentials.getDescription();
    }

    @NonNull
    @Override
    public String getId() {
        return stringCredentials.getId();
    }

    @Override
    public CredentialsScope getScope() {
        return stringCredentials.getScope();
    }

    @NonNull
    @Override
    public CredentialsDescriptor getDescriptor() {
        return stringCredentials.getDescriptor();
    }
}
