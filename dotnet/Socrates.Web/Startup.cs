using Microsoft.Owin;
using Owin;

[assembly: OwinStartup(typeof(Socrates.Web.Startup))]

namespace Socrates.Web
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            // Pour plus d'informations sur la configuration de votre application, visitez https://go.microsoft.com/fwlink/?LinkID=316888
        }
    }
}
