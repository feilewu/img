package github.resources.img.auth.subject;

public class Account implements Subject{

    private Principal principal;

    private Certificate certificate;

    public Account(Principal principal, Certificate certificate) {
        this.principal = principal;
        this.certificate = certificate;
    }

    public Account() {
    }

    public static Account create(String name,String password){
        Account account = new Account();
        AccountPrincipal accountPrincipal = new AccountPrincipal();
        accountPrincipal.setName(name);
        AccountCertificate accountCertificate = new AccountCertificate();
        accountCertificate.setBody(password);
        account.setPrincipal(accountPrincipal);
        account.setCertificate(accountCertificate);
        return account;
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public Certificate getCertificate() {
        return certificate;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }


    static class AccountPrincipal implements Principal{

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public String string() {
            return name;
        }
    }

    static class AccountCertificate implements Certificate {

        private String body;

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String body() {
            return body;
        }
    }

}
