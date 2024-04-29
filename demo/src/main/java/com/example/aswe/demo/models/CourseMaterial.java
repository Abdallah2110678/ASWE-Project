package  com.example.aswe.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.util.Objects;

@Entity
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String url;

    public CourseMaterial() {
    }

    public CourseMaterial(Long id, String type, String url) {
        this.id = id;
        this.type = type;
        this.url = url;
    }

    public Long getid() {
        return this.id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CourseMaterial id(Long id) {
        setid(id);
        return this;
    }

    public CourseMaterial type(String type) {
        setType(type);
        return this;
    }

    public CourseMaterial url(String url) {
        setUrl(url);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CourseMaterial)) {
            return false;
        }
        CourseMaterial courseMaterial = (CourseMaterial) o;
        return Objects.equals(id, courseMaterial.id) && Objects.equals(type, courseMaterial.type) && Objects.equals(url, courseMaterial.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, url);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getid() + "'" +
            ", type='" + getType() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
