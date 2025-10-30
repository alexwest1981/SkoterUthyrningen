package org.example.membership;

import java.util.List;

public class MembershipService {
    private final MemberRegistry memberRegistry;

    public MembershipService(MemberRegistry memberRegistry) {
        this.memberRegistry = memberRegistry;
    }

    /**
     * Skapar och lägger till ny medlem.
     */
    public Member addMember(String name, String statusLevel) {
        return memberRegistry.createAndAddMember(name, statusLevel);
    }

    public boolean removeMember(int memberId) {
        return memberRegistry.removeMember(memberId);
    }

    public boolean updateMemberStatus(int memberId, String newStatus) {
        Member m = memberRegistry.getMember(memberId);
        if (m != null) {
            m.setStatusLevel(newStatus);
            return true;
        }
        return false;
    }

    public List<Member> listAllMembers() {
        return memberRegistry.listMembers();
    }

    public List<Member> listByStatus(String statusLevel) {
        return memberRegistry.listByStatus(statusLevel);
    }
}