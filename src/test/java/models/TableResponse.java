package models;

import java.util.List;

public record TableResponse(
        String table,
        String no,
        String effectiveDate,
        List<Rate> rates
) {}