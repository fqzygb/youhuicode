package com.thinkgem.jeesite.modules.zhifubao.alipayConfig;

public class AlipayConfig {
    /**
     * 支付宝网关（固定）
     * 测试地址   https://openapi.alipaydev.com/gateway.do
     * 正式地址   https://openapi.alipay.com/gateway.do
     */
    public static final  String URL="https://openapi.alipay.com/gateway.do";

    /**
     * 授权url
     */
    public static final  String ALIPAY_URL = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm";
   // public static final String ALIPAY_URL = "https://openauth.alipaydev.com/oauth2/publicAppAuthorize.htm";
    /**
     * 应用id
     * 测试id 2021000116698389
     * 正式id 2021001187620168
     */
    public static final  String APP_ID="2017100909205589";

    /**
     * 应用私钥
     */
    public static final  String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCcduQ4EJqlYfG8Jr8l/Rxqc94li3ldAwjPQM6+wTRAJA6jgBMyGsBE8RoeFDFKfE+NchrB1sxDfpqD7wOKQhgRyAqHuhtbBwtzDI5/FltTixPELmej6/5H2J7gyOR//EA1CKg1MTaFpw5CFmqx+UKcxJXbqzzlCN1NmF5axT99S5wfBmGxSRdDiEOsmc5ptPAtFgdNYX1bXII8/pCQMhxmzseMtrv4VQ3IPzsUi0Oe+8xC1aBvN+5Lkj78zQkWV5gbs7rxBROhIvm6qIAgWhZJUu1D5a+phebSm5bq23tPpZ3CBzLYGObWDBVzPJkZytE7tRCE67np0o/h2BVzX6oRAgMBAAECggEAcCKBM3Eq9CY68oaubdJ2IBEEf9Hy2+dF1At1YJ7lDKcK2sD6gOlNLNvCxRXhsalf9lZ1uMBxylmc8btFxTvTNkMw0M9ftaBZ4013fruOHlTJiMtdOczzg5hLXXx8zwEBH0EiFGU91xHAFTstonmq9OGrKTETRm5Q9MCjttgwxWFdgVtUej9H8qCuUYQCARxZiiydPpqIMpaNCzsc7BTliQejdp5ID05KIWw02j5LQmoZR5GBN1vOaduAjTFgQ9tGWc+uYHRcQt1b9zEl6+fbRjJVyaJxkTcDSoqvYLJXEOvIrh152lqW5LV4gDM6ddKH2VCaKJPvTTN8eLfQXmVFFQKBgQDqW+ohHXyvfqUOnMzJQEGE1on7LC6BojclaWirbA/Q5XwoFOUaPtiR5SZGNPAZpB9ZceTrE0e0MebzEcE9ldMGSr5GzvlfTfCQtLq4V/LEdF7I6ZOo3LVAtzq+xz9JwDwmuD05fTucY9e16dY8rRShkQ0gQs/FoVF53JW+S5LNmwKBgQCq6ZodJurEu07IPOazGv6UtZAnmp53YZ6JuQDIOGIZdcAmy3IgRja6L9DGfPKTJwpG9AD+02wIu3stnDCl52Qqculzaa0lmt3IYsgCLDZsUKh9kK/aqiAhG88bPKgBg3L7y1T50bPdy9AL5YjyGRB6uDKjRpgon2l+NZd92453wwKBgBLS7ytZwvoVogxaa9h+YMTatpqGIfYnoWZP4ShjtaGBzUcq5nwFMEAHt9tLxT1KAXqdu1VmZwwCNhqwAmV/80M+HapyVXViPUBiQ90qs0U0U6ivmjyl/akFDqLkPiEeX7Z8de54MKGPI3RpI9wbytxvWi6NL0MggfrXzO9MAq6hAoGAPhae5fg9UTq6ZAO7y1UDJwLOW5TP/I56CY4rupDBEn60T2OZEiBvwTOdpH2/obWNBjmeIvSrSoDRPsB+68YjnUghhsvdGswnxaQdBK0LGE4052ioecdT5LM1ia9/WQ1nuBElzZtgvDJOYgQAu/JfCjXgL3B11kwTBsZxQkeAegECgYEAu4rcF/06+lOtFBo8mnWUSiilbJMFv59gfhjW+hWd8DHTCpYokAiuhTP5xDcK/MkQjxgC5iulpVJd3dUktTT/IsuDex47MWTt+hRm4g+DELrSh5m6j2wSvSeaXVjBP6eQx4uBn5OIhOIpPUzUFhXQggmZlwXw6uWugHO6x8+yb28=";
  // public static final String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC+9PJL/VTtTAlQhXPZRhZDda9FMtjly12vuUBG5XNVrTpc56uaAb7wO2DN1pbt/a2WKD+uqtHDV8A46dcWrVQvUx7/20eru2BxLPTZNi3RgmlLTOJCkbEnGUF50GyZKyw32vSvEPdzEmquCEQ6ghFQidS3lMHdlZsSSUA5JTQ/mfxWLxxvfdJ1GtVJXy+QNuNcIVcSJIvjCaZd/KRotbo/CMEkJ15PUl54D2zfYn8aDEBtaUZtq8F9kjJ3g0vXpeZ6x4M+UTj3U7VWwlLYLwrHSQ18thQ8ujU/J/zs6CqwA7bLpJpHmz2MOuEszV+Ty+o5OkYg3COMr9XHKIR5mG1DAgMBAAECggEBAKZ0juF+qxFPPq9aJWo4lh+n7mi7siZd6ATNzyH90VPV9RIXQJa2/uIaYIANlvNONoq1+w0fCkrlq0HxVPUHkmLOai9NzmP8qkz9i1Hs0HPY4kApfPHaXjpe4Zx1+rRp1dm6m6AfghRk8WlS+2alCsLR4wNEQNoPF2YRRFKMA8eLyZS5qnHMr0utuuIeR44Tev7YzPopDOSDKkn96nG53Kdkc5NQJzcTtoF523qR2GlU93AtDtnGEdDyDXisv6lNUSzvqehp0vN1sHW2c1Pw2mA7eATPbhdm7fd2A25ZD1FIJFc4XotUWdCf86vrw7yGplISlOqCuNN5sObPpNimpMECgYEA5Z7LEfb2153PVKq5K6aaKCKybQV7TG9Y9UmRa3cqcDBpeWgHCXPKzc4IxjD5CowbFRZqgq8hDTyhD1Cv+moIHDo5n5tekUvl3GFJIa3KUjT2q6g5Q/duaYpWufjTyGB2JFukSXaOQkGz4fnNnAnVLWx9OKOj1yLaiv1n/Y7uS9UCgYEA1OUMW3BbhotOxWpNo6M59i+hHzyQjUqtpl+hOjxXoz6elfWKSm3IGs3fqSKTYrkdzFoYCvJAFelhbKqY/V7JzUoM8cLD7Z+0GDe6U1rqKOHz6wYtW8QAPLXpMigKgATz9+TZUldv1LsWFo489cxhxVQjJ/V++nhOFbFobcjzWLcCgYEAgsrKfPchb3vSZzW/t990G7x3yaW/r7AVCdLX5FDCeZj520+oAnhUYpojYVDv6lQX8RENpla/Ct0oGKMrOgY/jGSWAhXr7fM/gSJhF6/4eI72mpx90fsUh0VnVRMo/xAC+36/u6CuM4tdPTCfL2xiYzdStHpxjs+pkFX7b/CER+kCgYBgDqDOuRly0XRPOrGhPcqvO7pVH7yRI7rKMKfMYOGh9GUVgF5PSs1Y2r8pz0H071V0mMn0Vm139X9Ryn+RAq3HBLxmcNx6YO6fZrWZWpIAtEKcLoNUvlDlE6cOTGbAcHurxAQSVQVOVMOYlcEHnpoWsOnT477RQGrq+ssFjf/ObQKBgQCgi8eHLa+ebCBG/rxswco6sTpKCzR3A0IsmsKfrSKXoaFa3NBclzTOXRsxJdH66d4Ve8PlIIWunJbuSb+d0iasHdoPGGZsvv52Fk68CKRGgwntescijACbIQpNGzdhSfmw6ceotVXDNZtZTCF2u1Dzt8jHuej58bJJ8zCcDsGM0A==";
    /*
     * 参数返回格式
     */
    public static final  String FORMAT="json";

    /**
     * 编码集
     */
    public static final  String CHARSET="UTF-8";

    /**
     * 支付宝公钥
     */
    public static final  String ALIPAY_PUBLIC_KEY= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs0oywx8vzeA+v/qw6XwvrWkyOKpkyAmFhQtPybDKSV8yVGdNLGF8pHclRZ7x+ck1g9iY1DPh8iXG/zeEnqkraZWbFny/0J8JazQPNxG99u5cWY26JS+4iz3clndnEUtbz8Niy69s6ixoqGSJaiAXvDWbn71RMyUe51dbv0LDjllerwHNhY/rXq4I+UyV6UygW/EEaW2Dr3vzIGIP9vx3Hq22TN1DknW29o4JDD253eLRgD6ns5BzLv+GV64Y2bymQAMiLUsFBGU31BSMZ1cU9hFz1KNV6kPzsnbL7/jRwDTLpBJIzjGjaN0+kPJoY19g5qdwdWXk/t1rxDXvLzZ63wIDAQAB";
    //public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArHK/5yQfJ3v/x6Tep0JPI5WExAK94TGhL0dlL7t4x6v2V9INejmod6iczngvOCX9awlmAOmXj1ihP0LINGaTBUiJBnDE+XOfu7yASg7Q4tFwIK9oiQ0RTtScZnxaH2rQaHa/qqHWCL3tt0MH03z4l3KYrztYKsHhH1hcZXlWzrBy8OFXZgbAKjE3/ArPoKc6zsHDkjGEcWJ67RP3GaBJDOyOMkdnrwzoDZoae4/hmPoCN01l53tRPw4RV5i8xMWaxEhIL21ICFpNCTylX6ESuP26pK40SIf4tvpwaLY2KCBFL8d8Qt++gUxUhfw1CBR/SKjCXRSdTKIwNReonfRUcQIDAQAB";
    /**
     * 商户生成签名字符串所使用的签名算法类型
     */
    public static final  String SIGN_TYPE="RSA2";
}
