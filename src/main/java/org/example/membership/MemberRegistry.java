package org.example.membership;

import java.util.*;

public class MemberRegistry {
    // Hanterar alla medlemmar i klubben
    // Medlemmarna lagras i ett HashMap och kan snabbt sökas på id
    private Map<Integer, Member> members;

    // Skapar ett nytt register utan medlemmar
    public MemberRegistry() {
        members = new HashMap<>();
    }

    // Lägger till medlem i registret
    public void addMember(Member member) {
        members.put(member.getId(), member);
    }

    // Tar bort medlem ur registret
    public boolean removeMember(int memberId) {
        return members.remove(memberId) != null;
    }

    // Hämtar medlem via id
    public Member getMember(int memberId) {
        return members.get(memberId);
    }

    // Returnerar en lista på alla medlemmar
    public List<Member> listMembers() {
        return new ArrayList<>(members.values());
    }

    // Filtrerar medlemmar på statusnivå
    public List<Member> filterByStatus(String statusLevel) {
        List<Member> result = new ArrayList<>();
        for (Member member : members.values()) {
            if (statusLevel.equalsIgnoreCase(member.getStatusLevel())) {
                result.add(member);
            }
        }
        return result;
    }
}
