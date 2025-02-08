package vn.com.t3h.antino.config;
import jakarta.servlet.*;

import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Lấy encoding từ cấu hình nếu có
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
        ServletContext context = filterConfig.getServletContext();
        // Đặt giá trị baseUrl vào ServletContext cho toàn bộ ứng dụng
        context.setAttribute("baseUrl", ConfigInit.BASE_URL);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Thiết lập mã hóa utf-8 cho request và response
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);

        filterChain.doFilter(servletRequest, servletResponse); // Tiếp tục chuỗi xử lý
    }


    @Override
    public void destroy() {
        // Cleanup nếu cần
    }
}
