package data_analytics;

import java.util.Set;

public interface ISpecificNumber {
    int getNumber();

    void setNumber(int number);

    @Override
    boolean equals(Object other);

    boolean equals(ISpecificNumber other);

    Set<Integer> getAllowed();
}
