package main.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostJSONObject {
    int hour;
    int minute;
    String requestId;
}
