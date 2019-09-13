package pl.pocket.myPocket.model.json;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Datasets {

    @Getter
    @Setter
    private double[] data;
    @Getter
    @Setter
    private String[] backgroundColor;

}
