package com.toughdevs.school.popugtasktracker.web;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.toughdevs.school.popugtasktracker.web.domain.Account;
import com.toughdevs.school.popugtasktracker.web.domain.TaskCreateRequest;
import com.toughdevs.school.popugtasktracker.web.domain.TaskData;

@RestController
public class TasksController {

	@Autowired
    private WebClient webClient;

    @GetMapping(value = "/tasks")
    public List<TaskData> getTasks(
      @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient
    ) {
        return this.webClient
          .get()
          .uri("http://127.0.0.1:8090/tasks")
          .attributes(oauth2AuthorizedClient(authorizedClient))
          .retrieve()
          .bodyToMono(List.class)
          .block();
    }
    
    @PostMapping(value = "/tasks/create")
    public TaskData createNewTask(TaskCreateRequest data,
      @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient
    ) {
        return this.webClient
          .post()
          .uri("http://127.0.0.1:8090/tasks/create")
          .bodyValue(data)
          .attributes(oauth2AuthorizedClient(authorizedClient))
          .retrieve()
          .bodyToMono(TaskData.class)
          .block();
    }
    
    @GetMapping(value = "/logout")
    public String[] logout(
      @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient
    ) {
        return this.webClient
          .get()
          .uri("http://127.0.0.1:9000/logout")
          .attributes(oauth2AuthorizedClient(authorizedClient))
          .retrieve()
          .bodyToMono(String[].class)
          .block();
    }
    
    @GetMapping(value = "/accounts")
    public List<Account> getAccounts(
      @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient
    ) {
        return this.webClient
          .get()
          .uri("http://127.0.0.1:9000/accounts")
          .attributes(oauth2AuthorizedClient(authorizedClient))
          .retrieve()
          .bodyToMono(List.class)
          .block();
    }
}
