package livraria;

public class NotificarViaSms implements Notificacao {

    @Override
    public void notificarCompra(Cliente cliente) {
        System.out.println("Notificando via SMS");
    }

}
