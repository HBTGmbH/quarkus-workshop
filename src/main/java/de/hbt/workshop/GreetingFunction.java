package de.hbt.workshop;

import io.quarkus.funqy.Funq;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class GreetingFunction {

    @Inject
    GreetingService service;

    @Funq
    public Map<String, String> funqy(Map<String, String> msg) {
        return Collections.singletonMap("hello", msg.get("name"));
    }

    @Funq
    public Greeting greet(Friend friend) {
        Greeting greeting = new Greeting();
        greeting.setMessage(service.greet(friend.getName()));
        return greeting;
    }
}
