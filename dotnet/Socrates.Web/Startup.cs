using Microsoft.Owin;
using Owin;
using System.Web.Http;

[assembly: OwinStartup(typeof(Socrates.Web.Startup))]

namespace Socrates.Web
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            // Configuration et services API Web
            var httpConfig = new HttpConfiguration();

            // Itinéraires de l'API Web
            httpConfig.MapHttpAttributeRoutes();

            httpConfig.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );

            app.UseWebApi(httpConfig);
        }
    }
}
