package com.sagar.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String,Account> nameVsAccountMap  = new HashMap<>();
        for(List<String> account:accounts) {
            String accountName = account.get(0);
            List<String> emails = account.subList(1,account.size());
            if(nameVsAccountMap.containsKey(accountName)) {
                nameVsAccountMap.get(accountName).insert(emails);
            } else {
                nameVsAccountMap.put(accountName,new Account(account));
            }
        }
        Set<Account> set  = new HashSet<>(nameVsAccountMap.values());
        List<List<String>> result = new ArrayList<>();
        for(Account acc:set) {
            result.addAll(acc.print());
        }
        return result;
    }


    public class Account {
        String account;
        Set<Set<String>> emails;
        Account(List<String> acc) {
            this.account = acc.get(0);
            this.emails = new HashSet<>();
            this.emails.add( new TreeSet<>(acc.subList(1,acc.size())));
        }
        public void insert(List<String> emails) {
            List<Set<String>> matchingSets = new ArrayList<>();
            for(Set set:this.emails) {
                for(String email:emails) {
                    if(set.contains(email)) {
                        matchingSets.add(set);
                        break;
                    }
                }
            }
            if(!matchingSets.isEmpty()) {
                Set<String> mergedSet= new TreeSet<>();
                for(Set set:matchingSets) {
                    mergedSet.addAll(set);
                    this.emails.remove(set);
                }
                mergedSet.addAll(emails);
                this.emails.add(mergedSet);

            } else {
                this.emails.add(new TreeSet<>(emails));
            }
        }
        public List<List<String>> print() {
            List<List<String>> arr = new ArrayList<>();
            for(Set set:emails) {
                List<String> acc = new ArrayList<>();
                acc.add(account);
                acc.addAll(set);
                arr.add(acc);
            }
            return arr;
        }
    }
}
