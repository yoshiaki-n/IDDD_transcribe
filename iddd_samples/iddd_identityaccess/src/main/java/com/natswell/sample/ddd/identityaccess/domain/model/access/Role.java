package com.natswell.sample.ddd.identityaccess.domain.model.access;

import java.util.UUID;

import com.natswell.sample.ddd.common.domain.model.ConcurrencySafeEntity;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.Group;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.TenantId;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.User;

/**
 * aggregate root
 * @author yoshiaki-n
 *
 */
public class Role extends ConcurrencySafeEntity {

    private static final long serialVersionUID = 1L;

    private String description;
    private Group group;
    private String name;
    private boolean supportsNesting = true;
    private TenantId tenantId;
    
//    public Role(TenantId aTenantId, String aName, String aDescription) {
//        // TODO Auto-generated constructor stub
//    }
//    
    public Role(TenantId aTenantId, String aName, String aDescription, boolean aSupportsNesting) {
        this();
        
        this.setDescription(aDescription);
        this.setName(aName);
        this.setSupportsNesting(aSupportsNesting);
        this.setTenantId(aTenantId);
        
        this.createInternalGroup();
    }
    
//    public void assignGroup(Group aGroup, GroupMemberService aGroupMemberService) {
//        
//    }
//
//    public void assignUser(User aUser) {
//        
//    }
//
//    public String description() {
//        return this.description;
//    }
//
//    public boolean isInRole(User aUser, GroupMemberService aGroupMemberService) {
//        
//    }

    public String name() {
        return this.name;
    }

//    public boolean supportsNesting() {
//        return this.supportsNesting;
//    }
//
    public TenantId tenantId() {
        return this.tenantId;
    }

//    public void unassignGroup(Group aGroup) {
//        
//    }
//
//    public void unassignUser(User aUser) {
//        
//    }
//
//    @Override
//    public boolean equals(Object anObject) {
//        
//    }
//
//    @Override
//    public int hashCode() {
//        
//    }
//
//    @Override
//    public String toString() {
//        
//    }
    
    public Role() {
        super();
    }

    protected void createInternalGroup() {
        String groupName = 
                Group.ROLE_GROUP_PREFIX
                + UUID.randomUUID().toString().toUpperCase();
        
        this.setGroup(new Group(
                this.tenantId(),
                groupName, 
                "Role backing group for: " + this.name()));
    }

    protected void setDescription(String aDescription) {
        this.assertArgumentNotEmpty(aDescription, "Role description is required.");
        this.assertArgumentLength(aDescription, 1, 250, "Role description must be 250 characters or less.");
        
        this.description = aDescription;
    }
    
//    protected Group group() {
//        return this.group;
//    }
//
    protected void setGroup(Group aGroup) {
        this.group = aGroup;
    }

    protected void setName(String aName) {
        this.assertArgumentNotEmpty(aName, "Role name must be provided.");
        this.assertArgumentLength(aName, 1, 250, "Role name must be 250 characters or less");
        
        this.name = aName;
    }
    
    protected void setSupportsNesting(boolean aSupportsNesting) {
        this.supportsNesting = aSupportsNesting;
    }

    protected void setTenantId(TenantId aTenantId) {
        this.assertArgumentNotNull(aTenantId, "the tenantId is required.");
        
        this.tenantId = aTenantId;
    }
}
