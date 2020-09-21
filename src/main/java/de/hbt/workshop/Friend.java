package de.hbt.workshop;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@RegisterForReflection
public class Friend {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
