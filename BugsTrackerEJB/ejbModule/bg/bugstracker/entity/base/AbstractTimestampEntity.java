package bg.bugstracker.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractTimestampEntity {

    @Temporal(TemporalType.DATE)
    @Column(name = "created_dt", nullable = false, insertable = false, updatable=false)
    private Date create_dt;

    @Temporal(TemporalType.DATE)
    @Column(name = "modify_dt", nullable = false, insertable = false, updatable=false)
    private Date modify_dt;

    @PrePersist
    protected void onCreate() {
    	this.setModify_dt(new Date());
    	this.setCreate_dt(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
    	this.setModify_dt(new Date());
    }

	public Date getCreate_dt() {
		return create_dt;
	}

	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}

	public Date getModify_dt() {
		return modify_dt;
	}

	public void setModify_dt(Date modify_dt) {
		this.modify_dt = modify_dt;
	}

	
}
