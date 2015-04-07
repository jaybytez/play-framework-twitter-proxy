package models.twitter;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "response")
public class Statuses {

    private List<Status> statuses;
    
    public Statuses() {
    }

    public Statuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    @XmlElement(name="status")
    @XmlElementWrapper(name="statuses")
    public List<Status> getStatuses() {
        return statuses;
    }

}
