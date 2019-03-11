package data_analytics;

import java.util.Set;

public class SpecificNumber implements ISpecificNumber {
    private int number;
    private Set<Integer> allowed;

    public SpecificNumber(int number, Set<Integer> allowed) {
        this.allowed = allowed;
        setNumber(number);
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public Set<Integer> getAllowed() {
        return allowed;
    }

    @Override
    public void setNumber(int number) {
        if (allowed.stream().anyMatch(n -> n == number)) {
            this.number = number;
        } else {
            throw new IllegalArgumentException("number not allowed");
        }
    }


    @Override
    public boolean equals(Object other){
        if(other.getClass() == this.getClass()){
           return this.equals(other);
        }else{
            return false;
        }
    }

    @Override
    public boolean equals(ISpecificNumber other){
        return this.getNumber() == other.getNumber() && this.allowed.size() == other.getAllowed().size()
                && this.allowed.stream().allMatch(n-> other.getAllowed().stream().anyMatch(o->o==n));
    }
}
