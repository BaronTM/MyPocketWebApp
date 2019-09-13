package pl.pocket.myPocket.model.json;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    @Getter
    @Setter
    private Datasets datasets;
    @Getter
    @Setter
    private String[] labels;

}
