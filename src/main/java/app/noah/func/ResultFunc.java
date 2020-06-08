package app.noah.func;

import com.querydsl.core.QueryResults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultFunc
{
    public static Map<String, Object> getResult(QueryResults<?> queryResults, Boolean isPaging)
    {
        Map<String, Object> result = new HashMap<>();

        if (queryResults != null) {
            long total = queryResults.getTotal();

            result.put("data", queryResults.getResults());
            result.put("isEmpty", queryResults.isEmpty());
            result.put("contentTotal", total);

            if (isPaging) {
                long offset = queryResults.getOffset();
                long limit = queryResults.getLimit();

                result.put("offset", offset);
                result.put("limit", limit);

                result.put("pageNum", offset / limit);
                result.put("pageTotal", total / limit + (total % limit > 0 ? 1 : 0));
            }
        } else {
            result.put("data", new ArrayList<>());
            result.put("contentTotal", 0);
            result.put("offset", 0);
            result.put("limit", 0);

            result.put("pageNum", 0);
            result.put("pageTotal", 0);
        }

        return result;
    }
}
