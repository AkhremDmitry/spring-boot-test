package com.epam.springboottest.events;

import com.epam.springboottest.github.GithubClient;
import com.epam.springboottest.github.RepositoryEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventsController {

    private GithubProjectRepository repository;

    private GithubClient githubClient;

    public EventsController(GithubProjectRepository repository, GithubClient githubClient) {
        this.repository = repository;
        this.githubClient = githubClient;
    }

    @GetMapping("/events/{repoName}")
    @ResponseBody
    public RepositoryEvent[] fetchEvents(@PathVariable String repoName){
        GithubProject project = this.repository.findByRepoName(repoName);

        return this.githubClient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
    }
}
