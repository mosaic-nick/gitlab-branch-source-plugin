package io.jenkins.plugins.gitlabbranchsource.helpers;

import io.jenkins.plugins.gitlabserverconfig.credentials.PersonalAccessToken;
import io.jenkins.plugins.gitlabserverconfig.servers.GitLabServer;
import io.jenkins.plugins.gitlabserverconfig.servers.GitLabServers;
import org.gitlab4j.api.GitLabApi;

public class GitLabHelper {

    public static GitLabApi apiBuilder(String serverName) throws NoSuchFieldException {
        GitLabServer server = GitLabServers.get().findServer(serverName);
        if(server != null) {
            PersonalAccessToken credentials = GitLabServer.getCredentials(server.getCredentialsId(), server.getServerUrl());
            if(credentials != null) {
                return new GitLabApi(server.getServerUrl(), credentials.getToken().getPlainText());
            }
            return new GitLabApi(server.getServerUrl(), GitLabServer.EMPTY_TOKEN);
        }
        throw new NoSuchFieldException(String.format("No server found with the name: %s", serverName));
    }

}
