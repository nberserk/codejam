package design.connect4.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Point {
    private final int x;
    private final int y;
}
