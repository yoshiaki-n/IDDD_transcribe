package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.natswell.sample.ddd.common.domain.model.ConcurrencySafeEntity;

/**
 * aggregate root
 * @author yoshiaki-n
 *
 */
public class Group extends ConcurrencySafeEntity {

    private static final long serialVersionUID = 1L;
    
    public static final String ROLE_GROUP_PREFIX = "ROLE-INTERNAL-GROUP";
    
    private String description;
    private Set<GroupMember> groupMembers;
    private String name;
    private TenantId tenantId;
    
    public Group(TenantId aTenantId, String aName, String aDescription) {
        this();
        
        this.setDescription(aDescription);
        this.setName(aName);
        this.setTenantId(aTenantId);
    }
    
//    public void addGroup(Group agroup, GroupMemberService aGroupMemberService) {
//        
//    }
//    
//    public void addUser(User aUser) {
//        
//    }
//
//    public String description() {
//        return this.description;
//    }
//
//    public Set<GroupMember> groupMembers() {
//        return this.groupMembers;
//    }
//
//    public boolean isMember(User aUser, GroupMemberService aGroupMemberService) {
//        return false;
//    }

    public String name() {
        return this.name;
    }
    
//    public void removeGroup(Group aGroup) {
//        
//    }
//    
//    public void removeUser(User aUser) {
//        
//    }
//    
//    public TenantId tenantId() {
//        return this.tenantId;
//    }
//
//    @Override
//    public boolean equals(Object anObject) {
//        return false;
//    }
//    @Override
//    public int hashCode() {
//        return 0;
//    }
//    
//    @Override
//    public String toString() {
//        return null;
//    }
//
    protected Group() {
        super();

        this.setGroupMembers(new HashSet<GroupMember>(0));
    }

    protected void setDescription(String aDescription) {
        this.assertArgumentNotEmpty(aDescription, "Group description is required.");
        this.assertArgumentLength(aDescription, 1, 250, "Group description must be 250 characters or less.");
        
        this.description = aDescription;
    }

    protected void setGroupMembers(Set<GroupMember> aGroupMembers) {
        this.groupMembers = aGroupMembers;
    }

    protected boolean isInternalGroup() {
        return this.isInternalGroup(this.name());
    }

    protected boolean isInternalGroup(String aName) {
        return aName.startsWith(ROLE_GROUP_PREFIX);
    }

    protected void setName(String aName) {
        this.assertArgumentNotEmpty(aName, "Group name is required.");
        this.assertArgumentLength(aName, 1, 100, "Group name must be 100 characters or less.");
        
        if (this.isInternalGroup(aName)) {
            String uuid = aName.substring(ROLE_GROUP_PREFIX.length());
            
            try {
                UUID.fromString(uuid);
            } catch (Exception e) {
                throw new IllegalArgumentException("The group name has an invalid format.");
            }
        }
        
        this.name = aName;
    }

    protected void setTenantId(TenantId aTenantId) {
        this.assertArgumentNotNull(aTenantId, "The tenantId must be provided.");
        
        this.tenantId = aTenantId;
    }

//    protected GroupMember toGroupMember() {
//        
//    }
}
