package leets.leenk.global.common.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public class PageableMapperUtil {
    public static CommonPageableResponse from(Slice<?> slice) {
        return CommonPageableResponse.builder()
                .pageNumber(slice.getNumber())
                .pageSize(slice.getSize())
                .numberOfElements(slice.getNumberOfElements())
                .hasNext(slice.hasNext())
                .empty(slice.isEmpty())
                .build();
    }

    public static CommonPageableResponse from(Page<?> page) {
        return CommonPageableResponse.builder()
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .numberOfElements(page.getNumberOfElements())
                .hasNext(page.hasNext()) // or isLast(), etc.
                .empty(page.isEmpty())
                .build();
    }
}
