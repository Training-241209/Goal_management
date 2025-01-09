import { Button } from "./ui/button";

export function Navbar() {
  return (
    <div className="fixed top-0 left-0 w-full bg-white shadow-md z-50">
        <div className="2xl:container mx-auto flex justify-between items-center px-14 py-5">
            <div className="text-2xl font-bold">AIMZY</div>
            <div className="gap-2">
                <Button className="px-6 py-3 text-sm">Login</Button>
            </div>
        </div>
    </div>
  );
}
