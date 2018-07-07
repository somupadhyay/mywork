package org.bootiful.react.example.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Beer {

    @Override
	public String toString() {
		return "Beer [id=" + id + ", name=" + name + "]";
	}

	@Id
    @GeneratedValue
    private Long id;
    private String name;

    public Beer(String name) {
        this.name = name;
    }

    public Beer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
