package livraria;

public class NotificarViaEmail implements Notificacao {

    @Override
    public void notificarCompra(Cliente cliente) {
        System.out.println("Notificando compra via Email");
    }
}
