package projectB.meongbti.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private Status status;
    private String message;
    private T data;

    public static Response<Void> error(String message) {

        return new Response<>(Status.ERROR, message, null);
    }

    public static <T> Response<T> success(String message, T result) {

        return new Response<>(Status.SUCCESS, message, result);
    }
}
