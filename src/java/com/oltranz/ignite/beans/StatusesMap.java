/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ignitestatuses")
public class StatusesMap implements Serializable {
    @Id @Column(length=10)
            private String ignitestatusId;
    @Id @Column(length=100)
            private String  organization;
    @Column(length=10)
    private String oltranzstatus;
    @Column(length=200)
    private String description;
    

    /**
     * @return the ignitestatusId
     */
    public String getIgnitestatusId() {
        return ignitestatusId;
    }

    /**
     * @param ignitestatusId the ignitestatusId to set
     */
    public void setIgnitestatusId(String ignitestatusId) {
        this.ignitestatusId = ignitestatusId;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the oltranzstatus
     */
    public String getOltranzstatus() {
        return oltranzstatus;
    }

    /**
     * @param oltranzstatus the oltranzstatus to set
     */
    public void setOltranzstatus(String oltranzstatus) {
        this.oltranzstatus = oltranzstatus;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
