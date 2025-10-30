package org.example.membership;

import java.util.*;

public class MemberRegistry {
    private Map<Integer, Member> members = new HashMap<>();
    private int nextId = 1;

    /**
     * Skapar en ny medlem med unikt id, namn och status och lägger till i registret.
     */
    public Member createAndAddMember(String name, String statusLevel) {
        int id = nextId++;
        Member member = new Member(id, name, statusLevel);
        members.put(id, member);
        return member;
    }

    public boolean removeMember(int memberId) {
        return members.remove(memberId) != null;
    }

    public Member getMember(int memberId) {
        return members.get(memberId);
    }

    public List<Member> listMembers() {
        return new ArrayList<>(members.values());
    }

    public List<Member> listByStatus(String statusLevel) {
        List<Member> result = new ArrayList<>();
        for (Member m : members.values()) {
            if (m.getStatusLevel().equalsIgnoreCase(statusLevel)) {
                result.add(m);
            }
        }
        return result;
    }
}