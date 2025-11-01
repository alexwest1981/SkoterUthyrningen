package org.example.membership;

import java.util.List;

public class MembershipService {
    private final MemberRegistry memberRegistry;

    public MembershipService(MemberRegistry memberRegistry) {
        this.memberRegistry = memberRegistry;
    }

    /**
     * Skapar och lägger till ny medlem, konverterar statusStr till enum.
     */
    public Member addMember(String name, String statusStr) {
        MemberRegistry.StatusLevel status = parseStatusLevel(statusStr);
        return memberRegistry.createAndAddMember(name, status);
    }

    public boolean removeMember(int memberId) {
        return memberRegistry.removeMember(memberId);
    }

    public boolean updateMemberStatus(int memberId, String newStatusStr) {
        Member m = memberRegistry.getMember(memberId);
        if (m != null) {
            MemberRegistry.StatusLevel status = parseStatusLevel(newStatusStr);
            m.setStatusLevel(status);
            return true;
        }
        return false;
    }

    public List<Member> listAllMembers() {
        return memberRegistry.listMembers();
    }

    public List<Member> listByStatus(String statusStr) {
        MemberRegistry.StatusLevel status = parseStatusLevel(statusStr);
        return memberRegistry.listByStatus(status);
    }

    public List<Member> searchMembersByName(String searchTerm) {
        return memberRegistry.searchByName(searchTerm);
    }

    public List<Member> filterMembersByStatus(String statusStr) {
        MemberRegistry.StatusLevel status = parseStatusLevel(statusStr);
        return memberRegistry.filterByStatus(status);
    }

    public Member getMember(int memberId) {
        return memberRegistry.getMember(memberId);
    }

    // Hjälpmetod för konvertering från sträng till enum med felhantering
    private MemberRegistry.StatusLevel parseStatusLevel(String statusStr) {
        try {
            return MemberRegistry.StatusLevel.valueOf(statusStr.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return MemberRegistry.StatusLevel.STANDARD; // Defaultvärde vid fel
        }
    }
}
