package projectB.meongbti.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String process;
    private T data;

    public static Response<Void> error(String errCode) {

        return new Response<>(errCode, null);
    }

    public static <T> Response<T> success(T result) {

        return new Response<>("성공", result);
    }
}
