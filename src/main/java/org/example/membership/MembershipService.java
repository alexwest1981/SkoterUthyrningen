package org.example.membership;

import java.util.List;

public class MembershipService {
    // Sköter logik kring medlemskap, status och medlemsregister
    private MemberRegistry memberRegistry;

    // Skapar en ny MembershipService
    public MembershipService(MemberRegistry memberRegistry) {
        this.memberRegistry = memberRegistry;
    }

    // Lägger till en ny medlem
    public Member addMember(String name, String statusLevel) {
        int newId = generateUniqueId();
        Member member = new Member(newId, name, statusLevel);
        memberRegistry.addMember(member);
        return member;
    }

    // Tar bort medlem baserat på id
    public boolean removeMember(int memberId) {
        return memberRegistry.removeMember(memberId);
    }

    // Returnerar alla medlemmar i registret
    public List<Member> listAllMembers() {
        return memberRegistry.listMembers();
    }

    // Returnerar medlemmar av viss status
    public List<Member> listMembersByStatus(String statusLevel) {
        return memberRegistry.filterByStatus(statusLevel);
    }

    // Genererar ett unikt medlems-id (enkelt, kan utökas)
    private int generateUniqueId() {
        return memberRegistry.listMembers().size() + 1;
    }
}
